from flask import Flask
from flask_cors import CORS
import requests
import json

app = Flask(__name__)
app.debug = True

CORS(app)

@app.route('/weather', methods=['GET'])
def weather():
    apikey = "474d59dd890c4108f62f192e0c6fce01"

    cities = ["Seoul,KR"]
    api = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}&units=metric"
    print(api)

    k2c = lambda k: k - 273.15
    print(k2c)

    for name in cities:
        url = api.format(city=name, key=apikey)
        r = requests.get(url)
        data = json.loads(r.text)
        print("+ 도시 =", data["name"])
        print(": 날씨 =", data["weather"][0]["description"])
        print(": 온도 =", data["main"]["temp"])
        print("")
    print(data)
    return data

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)