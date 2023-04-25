import React, { Component } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";

export class AddCandidate extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      birthdate: "",
      email: "",
      phone: "",
      newSkillName: "",
      skills: [],
      candidateSkills: [],
    };
  }

  async getAllSkills() {
    let data = await axios.get("http://localhost:8080/skills");
    this.setState({ skills: data.data }, () => console.log(this.state.skills));
  }

  async addCandidate() {
    if (
      (this.state.name == "" || this.state.email == "",
      this.state.phone == "",
      this.state.birthdate == "")
    ) {
      alert("Error. Fill all inputs with data and try again.");
      return;
    }
    console.log("skills", this.state.candidateSkills)
    await axios.post("http://localhost:8080/add-candidate", {
      fullName: this.state.name,
      email: this.state.email,
      contactNumber: this.state.phone,
      candidateSkills: this.state.candidateSkills,
      birthDate: this.state.birthdate,
    });
    alert("Candidate has been successfully added to database!");
    window.location.href = "http://localhost:8080/";
  }
  componentDidMount() {
    this.getAllSkills();
  }

  render() {
    return (
      <div
        style={{
          backgroundColor: "gray",
          margin: 30,
        }}
      >
        <p style={{ fontSize: 20, padding: 20, textAlign: "center" }}>
          Add a new candidate to the database
        </p>
        <div
          style={{
            alignSelf: "center",
            justifyContent: "center",
            display: "flex",
            flexDirection: "column",
            flex: 1,
          }}
        >
          <label
            style={{
              alignSelf: "center",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              width: 250,
            }}
          >
            Name
            <input
              type="text"
              value={this.state.name}
              onChange={(text) => this.setState({ name: text.target.value })}
            />
          </label>
          <label
            style={{
              alignSelf: "center",
              display: "flex",
              flexDirection: "column",
              width: 250,
            }}
          >
            Phone number
            <input
              type="text"
              value={this.state.phone}
              onChange={(text) => this.setState({ phone: text.target.value })}
            />
          </label>
          <label
            style={{
              alignSelf: "center",
              display: "flex",
              flexDirection: "column",
              width: 250,
            }}
          >
            E-mail
            <input
              type="text"
              value={this.state.email}
              onChange={(text) => this.setState({ email: text.target.value })}
            />
          </label>
          <label
            style={{
              alignSelf: "center",
              display: "flex",
              flexDirection: "column",
              width: 250,
            }}
          >
            Birthdate
            <input
              type="date"
              onChange={(data) =>
                this.setState({ birthdate: data.target.value }, () =>
                  console.log(this.state.birthdate)
                )
              }
              value={this.state.birthdate}
            />
          </label>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              alignSelf: "center",
            }}
          >
            {this.state.skills.map((item) => {
              return (
                <label
                  style={{
                    marginRight: 16,
                    display: "flex",
                    alignItems: "center",
                  }}
                >
                  <input
                    type="checkbox"
                    style={{ marginRight: 4 }}
                    checked={
                      this.state.candidateSkills.filter(
                        (skillId) => skillId == item.id
                      ).length == 1
                    }
                    onChange={() => {
                      console.log(item.title);
                      if (
                        this.state.candidateSkills.filter(
                          (skillId) => skillId == item.id
                        ).length == 1
                      ) {
                        this.setState(
                          {
                            candidateSkills: this.state.candidateSkills.filter(
                              (skillId) => skillId != item.id
                            ),
                          },
                          () => console.log(this.state.candidateSkills)
                        );
                      } else {
                        this.setState(
                          {
                            candidateSkills: [
                              ...this.state.candidateSkills,
                              item.id,
                            ],
                          },
                          () => console.log(this.state.candidateSkills)
                        );
                      }
                    }}
                  />
                  {item.title}
                </label>
              );
            })}
          </div>
          <label
            style={{
              alignSelf: "center",
              display: "flex",
              flexDirection: "column",
            }}
          >
            New skill
            <span style={{ display: "flex", alignItems: "center" }}>
              <input
                type="text"
                value={this.state.newSkillName}
                onChange={(text) =>
                  this.setState({ newSkillName: text.target.value })
                }
                style={{
                  width: 250,
                }}
              />
              <a
                onClick={async () => {
                  await axios.post("https://localhost:8080/add-skill", {
                    name: this.state.newSkillName,
                  });
                  this.setState({ newSkillName: "" });
                  this.getAllSkills();
                }}
                style={{ marginLeft: 10 }}
              >
                <img
                  style={{ height: 35, width: 35 }}
                  src="https://img.icons8.com/material-rounded/100/000000/add.png"
                />
              </a>
            </span>
          </label>
          <a
            onClick={() => {
              this.addCandidate();
            }}
          >
            <p>Add Candidate</p>
          </a>
        </div>
      </div>
    );
  }
}
