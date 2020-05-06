const express = require("express");
const bodyParser = require("body-parser");
const PORT = process.env.PORT || 3000;
const app = express();

// parse requests of content-type: application/json
app.use(bodyParser.json());

// parse requests of content-type: application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

require("./routes/routes.js")(app);

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to mysql application." });
});

// set port, listen for requests
app.listen(PORT, () => {
  console.log("Server is running on port 3000.");
});