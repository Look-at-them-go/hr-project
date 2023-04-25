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
    window.location.href = "http://localhost:3000/";
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
