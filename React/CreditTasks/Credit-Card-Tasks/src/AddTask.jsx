import React, { useState } from "react";

// import "../node_modules/bootstrap/dist/css/bootstrap.min.css";

const DailyTasks = () => {
  const [dailyTasks, setDailyTasks] = useState([]);
  const [taskEntry, setTaskEntry] = useState({});

  // Updates the values in each input field
  const handleChange = (event) => {
    const { name, value } = event.target;
    // values is "old" value of state variable
    setTaskEntry((values) => ({ ...values, [name]: value }));
  };

  const DisplayTask = () => {
    return (
      <table className="task-display">
        <thead>
          <tr>
            <th>Task</th>
            <th>Wager</th>
            <th>Deadline</th>
          </tr>
        </thead>
        <tbody>
          {Array.prototype.map.call(dailyTasks, (data, index) => (
            <tr key={index}>
              <td>{data.text}</td>
              <td>{data.wager}</td>
              <td>{data.deadline}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  };

  // Adds the task to the directory
  const handleSubmit = (event) => {
    event.preventDefault();
    setDailyTasks((currentTasks) => [...currentTasks, taskEntry]);
    console.log(dailyTasks);
  };

  return (
    <div className="payment-app">
      <head>
        <title>Daily Task Logger</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
      </head>
      <body>
        <h1>Daily Task Logger</h1>
        <form class="task-form" method="post" onSubmit={handleSubmit}>
          <label for="text">Task</label>
          <textarea
            type="text"
            id="text"
            name="text"
            className="task-input"
            placeholder="TO DO: "
            value={taskEntry.text || ""}
            onChange={handleChange}
          />
          <label for="wager">Wager</label>
          <span>
            <label for="wager">$</label>
            <input
              type="number"
              name="wager"
              id="wager"
              className="task-input"
              placeholder="Cost"
              value={taskEntry.wager || ""}
              onChange={handleChange}
            />
          </span>
          <label for="text">Deadline</label>
          <input
            className="task-input"
            type="date"
            name="deadline"
            id="deadline"
            value={taskEntry.deadline || ""}
            onChange={handleChange}
          />
          <input id="add-task-btn" type="submit" />
        </form>
        <script src="app.js"></script>
        <br/>
        <br/>
        <br/>
        <DisplayTask />
      </body>
    </div>
  );
};

export default DailyTasks;

//onClick={() => addDailyTask('Complete report')}
