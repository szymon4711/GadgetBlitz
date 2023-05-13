FROM mongo

COPY init.json /init.json
CMD mongoimport --host mongo --db database --collection phones --type json --file /init.json --jsonArray