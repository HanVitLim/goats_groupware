module.exports = {
  user: "goats", // Oracle 사용자 이름
  password: "goats00", // Oracle 비밀번호
  connectString: "192.168.0.35/orclLSB00",
};

oracledb.initOracleClient({ libDir: "./instantclient_11_2" });
// 클라이언트 다운로드 안할시 실행x oracledb로node-oracledb
