from flask import Flask, jsonify
import urllib.request
import os,requests
from flask_cors import CORS
import xml.etree.ElementPath
from xml.etree.ElementTree import parse
import xml.etree.ElementTree as ET
import re
import json
import xmltodict
from collections import OrderedDict
from pymongo import MongoClient
import sys

app = Flask(__name__)

CORS(app)

@app.route('/news', methods=['GET'])
def news():

    url = "https://www.chosun.com/arc/outboundfeeds/rss/?outputType=xml"
    data = urllib.request.urlopen(url).read()

    text = data.decode("UTF-8")

    savename = "data/ajax_chosun_rss.xml"

    with open(savename, mode="wt", encoding="UTF-8") as f:
        f.write(text)
        print(savename, "저장되었습니다.")

    tree = parse(savename)

    root = tree.getroot()

    channel = root.find("channel")

    item = channel.findall('item')

    file_data = OrderedDict()

    a = 0

    for i in item[0:10]:
        file_data["_id"] = 1
        title = i.findtext('title')
        file_data["title" + str(a)] = title
        link = i.findtext('link')
        file_data["link" + str(a)] = link
        a = a + 1
        print(title, " : ", link)

    json_data = json.dumps(file_data, ensure_ascii=False, indent="\t")

    savejson = "data/test.json"

    with open(savejson, mode="wt", encoding="UTF-8") as f:
        f.write(json_data)
        print(savejson, "저장되었습니다.")

    with open(savejson, mode="r", encoding="UTF-8") as f:
        data_json = f.read()
        print(data_json)

    print(type(data_json))

    json_news = json.loads(data_json)
    client = MongoClient('192.168.0.59', 27017)
    db = client['goats']

    lennews = db.news.find_one({"_id": 1})

    print(lennews)
    print(type(lennews))

    if lennews == None:
        try:
            dbInsert = db.news.insert_one(json_news)

        except:
            print("insert failed", sys.exc_info()[0])
    else:
        try:
            dbupdate = db.news.update_one({"_id": 1}, {"$set": json_news})
        except:
            print("update failed", sys.exc_info()[0])

    # print(dbInsert.inserted_id)
    """
    try:
        dbDelete = db.news.delete_one({"_id" : 1})
    except:
        print("Delete failed", sys.exc_info()[0])
    """

    return data

if __name__ == "__main__" :
    app.run(host='0.0.0.0', port=5001, debug=True)