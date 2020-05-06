module.exports = app => {
    const tests = require("../controllers/test.controller.js");
  
    // Create a new Test
    app.post("/tests", tests.create);
  
    // Retrieve all Tests
    app.get("/tests", tests.findAll);
  
    // Retrieve a single Test with testId
    app.get("/tests/:testId", tests.findOne);
  
    // Update a Test with testId
    app.put("/tests/:testId", tests.update);
  
    // Delete a Test with testId
    app.delete("/tests/:testId", tests.delete);
  
    // Delete all Tests
    app.delete("/tests", tests.deleteAll);
};