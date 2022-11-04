import math
import json
import requests

def lambda_handler(event, context):
    print(event)

    event = event.get('body')

    if not event:
        return {
            'statusCode': 400,
            'body': "Body de requisição inexistente"
        }

    body = json.loads(event)


    try:
        req = requests.get(f'https://distancep.herokuapp.com/distance/{body["cep1"]}/{body["cep2"]}').json()
        if 'error' in req.keys():
            raise Exception('Erro na chamada da API de calculo de distancia')
    except Exception as e:
        print(f'{e = }')
        req = {
            'cepsInfo': None,
            'distance': round(calculate_distance(cep1=body["cep1"], cep2=body["cep2"]), 1),
        }

    print(req)

    return {
        'statusCode': 200,
        'body': json.dumps(req)
    }

def calculate_distance(cep1, cep2):

    info_cep1 = requests.get(f'https://viacep.com.br/ws/{cep1}/json').json()
    info_cep2 = requests.get(f'https://viacep.com.br/ws/{cep2}/json').json()

    geoinfo_cep1 = requests.get(f'https://api.geoapify.com/v1/geocode/search?street={info_cep1["logradouro"]}&postcode={cep1}&city={info_cep1["localidade"]}&state={info_cep1["uf"]}&country=Brazil&limit=1&format=json&apiKey=03197b59532d488d8529d2c3d663c4d5').json()
    geoinfo_cep2 = requests.get(f'https://api.geoapify.com/v1/geocode/search?street={info_cep2["logradouro"]}&postcode={cep2}&city={info_cep2["localidade"]}&state={info_cep2["uf"]}&country=Brazil&limit=1&format=json&apiKey=03197b59532d488d8529d2c3d663c4d5').json()

    latitude_cep1 = math.radians(geoinfo_cep1['results'][0]['lat'])
    longitude_cep1 = math.radians(geoinfo_cep1['results'][0]['lon'])
    latitude_cep2 = math.radians(geoinfo_cep2['results'][0]['lat'])
    longitude_cep2 = math.radians(geoinfo_cep2['results'][0]['lon'])

    dlat = latitude_cep2 - latitude_cep1
    dlon = longitude_cep2 - longitude_cep1

    sinDlatSquare = math.pow(math.sin(dlat / 2), 2)
    sinDlonSquare = math.pow(math.sin(dlon / 2), 2)

    a = sinDlatSquare + math.cos(latitude_cep1) * math.cos(latitude_cep2) * sinDlonSquare

    b = 2 * (math.asin(math.sqrt(a)))

    distance = 6_371 * b

    print(distance)

    return distance

# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1
