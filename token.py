import http.client

conn = http.client.HTTPSConnection("dev-qi8b5nrabbvawh0y.us.auth0.com")

payload = "{\"client_id\":\"sbj7qQLLPsUFrNliyCs6ZmDZ8olbDP99\",\"client_secret\":\"pqJQbRcEEfCetvbS7BMPi9XgM4du1MMPN5nqlzjgoUUK7IVeEJHXkb51VQ45CVEE\",\"audience\":\"https://user\",\"grant_type\":\"client_credentials\"}"

headers = { 'content-type': "application/json" }

conn.request("POST", "/oauth/token", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))