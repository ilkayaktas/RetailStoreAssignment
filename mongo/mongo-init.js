conn = new Mongo();
db = conn.getDB("retailstore")
db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "retailstore"
            }
        ]
    }
);