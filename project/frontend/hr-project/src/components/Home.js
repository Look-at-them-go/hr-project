import React, { Component } from "react";
import axios from "axios";

export class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      candidates: [],
      skills: [],
      skillIds: [],
      searchByName: '',
      searchBySkill: '',
      skillid: '',
      skillidR: ''
    };
  }

  async getUsers() {
    let data = await axios.get(`http://localhost:8080/candidates`);
    console.log("all cadidates:", data.data);
    this.setState({ candidates: data.data });
  }

  async deleteUser(user) {
    this.setState((prevState) => ({
      candidates: prevState.candidates.filter((e) => e != user),
    }));
    await axios.delete(`http://localhost:8080/delete-candidate/${user.id}`);
    alert("Candidate has been removed from the list.");
  }

  async getAllSkills() {
    let data = await axios.get("http://localhost:8080/skills");
    this.setState({ skills: data.data }, () => console.log(this.state.skills));
  }

  async findBySkill() {
    let string = null;
    let index = 0;

    this.state.skills.forEach(skill => { 
      string = `${skill.name}`;
      if (index != this.state.skills.length - 2) {
        string += ",";
      }
      index++;
    });

    let data = await axios.get(`http://localhost:8080/search-by-skill/${string}`);
    this.setState({ candidates: data.data });
  }

  async searchName() {
    let data = await axios.get(`http://localhost:8080/search-by-name/${this.state.searchByName}`);
    this.setState({ candidates: data.data });
  }

  async searchSkill() {
    let data = await axios.get(`http://localhost:8080/search-by-skill/${this.state.searchBySkill}`);
    this.setState({ candidates: data.data });
  }

  onInputChangeName = (e) => {
    this.setState({searchByName: e.target.value});
  }

  onInputChangeSkill = (e) => {
    this.setState({searchBySkill: e.target.value});
  }

  handleKeyPressName(e) {
    if (e.key === 'Enter') {
        this.searchName(); 
    }
  }

  handleKeyPressSkill(e) {
    if (e.key === 'Enter') {
        this.searchSkill(); 
    }
  }

  async deleteSkill(skill) {
    this.setState((prevState) => ({
      skills: prevState.skills.filter((e) => e.id != skill.id),
    }));
    this.getUsers();
    await axios.delete(`http://localhost:8080/delete-skill/${skill.id}`);
    alert("Skill has been removed from the list.");
    
  }

  async addSkill(user,skillid){

    await axios.put(`http://localhost:8080/${user.id}/assign-skill/${skillid}`);
    alert("Skill added");
    window.location.href = "http://localhost:3000/";
  }

  async removeSkill(user,skillid){

    await axios.put(`http://localhost:8080/${user.id}/remove-skill/${skillid}`);
    alert("Skill removed");
    window.location.href = "http://localhost:3000/";
  }

  componentDidMount() {
    this.getUsers();
    this.getAllSkills();
  }

  render() {
    return (
      <div>
        <div>
          <input type="text" value={this.state.searchByName} onChange={e => this.onInputChangeName(e)} placeholder="Search by name"  onKeyUp={this.handleKeyPressName.bind(this)}/>
          <input type="text" value={this.state.searchBySkill} onChange={e => this.onInputChangeSkill(e)} placeholder="Search by skills (id)"  onKeyUp={this.handleKeyPressSkill.bind(this)}/>
        </div>
        <table className="table table-striped" aria-labelledby="tabelLabel">
          <thead>
            <tr>
              <th>ID</th>
              <th>Full name</th>
              <th>Birthdate</th>
              <th>Phone number</th>
              <th>E-mail</th>
              <th>Skills</th>
              <th>Add Skill</th>
              <th>Remove Skill</th>
            </tr>
          </thead>
          <tbody>
            {this.state.candidates.map((user, index) => {
              let date = new Date(user.birthDate);
              return (
                <tr key={index}>
                  <td>{user.id}</td>
                  <td>{user.fullName}</td>
                  <td>
                    {date.getDate()}.{date.getMonth() + 1}.{date.getFullYear()}.
                  </td>
                  <td>{user.contactNumber}</td>
                  <td>{user.email}</td>
                  <td>
                    {user.candidateSkills.map((skill) => {
                      let string = `${skill.name}`;
                      if (index != user.candidateSkills.length - 2) {
                        string += ", ";
                      }
                      return string;
                    })}
                  </td>
                  <td>
                    <input type="text" value = {this.state.skillid} placeholder="Add skill (id)" onChange = {e => this.setState({skillid: e.target.value})} />
                    <a onClick={() => this.addSkill(user,this.state.skillid)}>ADD</a>
                  </td>
                  <td>
                    <input type="text" value = {this.state.skillidR} placeholder="Remove skill (id)" onChange = {e => this.setState({skillidR: e.target.value})} />
                    <a onClick={() => this.removeSkill(user,this.state.skillidR)}>Remove</a>
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
                  await axios.post("http://localhost:8080/add-skill", {
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
        <table className="table table-striped" aria-labelledby="tabelLabel">
          <thead>
              <tr>
                <th>ID</th>
                <th>Skill name</th>
              </tr>
          </thead>
          <tbody>
            {this.state.skills.map((skill, index) =>{
              return(
                <tr key={index}>
                  <td>{skill.id}</td>
                  <td>{skill.name}</td>
                  <td>
                    <a
                      style={{ width: "100%" }}
                      onClick={() => this.deleteSkill(skill)}
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
