//Oracle 사용하기
//npm install oracledb --save

const express = require("express");
oracledb = require("oracledb");
const dbConfig = require("./dbConfig.js");
const cors = require("cors");

oracledb.autoCommit = true; //자동 커밋

// 익스프레스 객체 생성
var app = express();

// CORS 설정
app.use(cors());

// 기본 속성 설정
app.set("port", process.env.PORT || 3002);

// db 데이터 오라클 조회
app.post("/calendarload", function (req, res) {
  oracledb.getConnection(
    {
      user: dbConfig.user,
      password: dbConfig.password,
      connectString: dbConfig.connectString,
    },
    function (err, conn) {
      if (err) {
        throw err;
      }

      console.log("Oracle DB 연결 성공!!");

      var sql;

      sql = `SELECT 
      GPSUBJECT AS "title", 
      GPSTART AS "start", 
      GPEND AS "end",
      Writer,
      GPTYPE,
      GPLOCATION,
      GPPNUM,
      CASE 
          WHEN GPPNUM LIKE 'VA%' THEN '#0c265a'
          WHEN GPPNUM LIKE 'BU%' THEN '#d66060'
      END AS color
  FROM 
      GOAT_PLAN`;

      conn.execute(sql, [], function (err, result) {
        if (err) {
          throw err;
        }

        const data = result.rows.map((row) => ({
          title: row[0],
          start: row[1],
          end: row[2],
          color: row[7],

          extendedProps: {
            todo: [row[3], row[0], row[5], row[1], row[2]],
          },
        }));

        console.log(result); 
        res.send(data);

        doRelease(conn);
      });
    }
  );
});

//DB 종료
function doRelease(conn) {
  conn.release(function (err) {
    if (err) {
      throw err;
    }
  });
}

app.listen(3002, function () {
  console.log("listening on 3002");
});
