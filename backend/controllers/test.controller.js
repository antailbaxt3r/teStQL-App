const Test = require("../models/Test.js");

// Create and Save a new Test
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Test
    const test = new Test({
        title: req.body.title,
        testType: req.body.testType,
        score: req.body.score,
        maxMarks: req.body.maxMarks,
        testDate: req.body.testDate,
        testPattern: req.body.testPattern,
    });
  
    // Save Test in the database
    Test.create(test, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Internal server error"
        });
      else res.send(data);
    });
  };

// Retrieve all Tests from the database.
exports.findAll = (req, res) => {
    Test.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving tests."
        });
      else res.send(data);
    });
  };

// Find a single Test with a testId
exports.findOne = (req, res) => {
    Test.findById(req.params.testId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Test with id ${req.params.testId}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Test with id " + req.params.testId
          });
        }
      } else res.send(data);
    });
  };

// Update a Test identified by the testId in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Test.updateById(
      req.params.testId,
      new Test(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Test with id ${req.params.testId}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Test with id " + req.params.testId
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a Test with the specified testId in the request
exports.delete = (req, res) => {
    Test.remove(req.params.testId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Test with id ${req.params.testId}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Test with id " + req.params.testId
          });
        }
      } else res.send({ message: `Test was deleted successfully!` });
    });
  };

// Delete all Tests from the database.
exports.deleteAll = (req, res) => {
    Test.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all tests."
        });
      else res.send({ message: `All Tests were deleted successfully!` });
    });
  };