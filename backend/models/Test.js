const sql = require("./db.js");

// constructor
const Test = function(test) {
    this.title = test.title
    this.testType = test.testType
    this.score = test.score
    this.maxMarks = test.maxMarks
    this.testDate = test.testDate
    this.testPattern = test.testPattern
};

Test.create = (newTest, result) => {
  sql.query("INSERT INTO tests SET ?", newTest, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("Created test: ", { id: res.id, ...newTest });
    result(null, { id: res.id, ...newTest });
  });
};

Test.findById = (testId, result) => {
  sql.query(`SELECT * FROM tests WHERE id = ${testId}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found test: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Test with the id
    result({ kind: "not_found" }, null);
  });
};

Test.getAll = result => {
  sql.query("SELECT * FROM tests", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("tests: ", res);
    result(null, res);
  });
};

Test.updateById = (id, test, result) => {
  sql.query(
    "UPDATE tests SET title = ?, testType = ?, score = ?, maxMarks = ?, testDate = ?, testPattern = ? WHERE id = ?",
    [test.title, test.testType, test.score, test.maxMarks, test.testDate, test.testPattern, id],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Test with the id
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated test: ", { id: id, ...test });
      result(null, { id: id, ...test });
    }
  );
};

Test.remove = (id, result) => {
  sql.query("DELETE FROM tests WHERE id = ?", id, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Test with the id
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted test with id: ", id);
    result(null, res);
  });
};

Test.removeAll = result => {
  sql.query("DELETE FROM tests", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} tests`);
    result(null, res);
  });
};

module.exports = Test;