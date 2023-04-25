import React, { Component } from "react";
import axios from "axios";

export class FetchData extends Component {
  static displayName = FetchData.name;

  constructor(props) {
    super(props);
    this.state = {
      users: [],
    };
  }

  async getUsers() {
    let data = await axios.get(`https://localhost:44323/candidate/all`);
    console.log(data.data);
    this.setState({ users: data.data });
  }
  async deleteUser(user) {
    this.setState((prevState) => ({
      users: prevState.users.filter((e) => e != user),
    }));
    await axios.delete(`https://localhost:44323/candidate?id=${user.id}`);
    alert("Candidate has been removed from the list.");
  }

  componentDidMount() {
    this.getUsers();
  }

  render() {
    return (
      <div>
        <table className="table table-striped" aria-labelledby="tabelLabel">
          <thead>
            <tr>
              <th>ID</th>
              <th>Full name</th>
              <th>Birthdate</th>
              <th>Phone number</th>
              <th>E-mail</th>
              <th>Skills</th>
            </tr>
          </thead>
          <tbody>
            {this.state.users.map((user, index) => {
              let date = new Date(user.birthdate);
              return (
                <tr key={index}>
                  <td>{user.id}</td>
                  <td>{user.name}</td>
                  <td>
                    {date.getDate()}.{date.getMonth() + 1}.{date.getFullYear()}.
                  </td>
                  <td>{user.phoneNumber}</td>
                  <td>{user.email}</td>
                  <td>
                    {user.skills.map((skill) => {
                      let string = `${skill.title}`;
                      if (index != user.skills.length - 2) {
                        string += ", ";
                      }
                      console.log("string:", string);
                      return string;
                    })}
                  </td>
                  <td>
                    <a
                      style={{ width: "100%" }}
                      onClick={() => this.deleteUser(user)}
                    >
                      <img
                        style={{
                          height: 30,
                          width: "auto",
                          alignSelf: "center",
                        }}
                        src="https://img.icons8.com/material-outlined/96/ff0040/trash--v1.png"
                      />
                    </a>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  }
}
