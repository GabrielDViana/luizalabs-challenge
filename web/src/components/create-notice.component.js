import React, { Component } from "react";
import NoticeService from "../services/notice.service";
import { TextField } from '@material-ui/core';


export default class CreateNotice extends Component {
  constructor(props) {
    super(props);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onChangeDate = this.onChangeDate.bind(this);
    this.saveNotice = this.saveNotice.bind(this);
    this.newNotice = this.newNotice.bind(this);

    this.state = {
      id: null,
      phone: "",
      scheduleDate: "",
      messageContent: "",

      submitted: false
    };
  }

  onChangePhone(e) {
    this.setState({
      phone: e.target.value
    });
  }

  onChangeDate(e) {
    console.log(e)
    // this.setState({
    //   scheduleDate: e.target.value
    // });
  }

  saveNotice() {
    var data = {
      phone: this.state.phone,
      scheduleDate: this.state.scheduleDate
    };

    NoticeService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          phone: response.data.phone,
          scheduleDate: response.data.scheduleDate,
          messageContent: response.data.messageContent,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newNotice() {
    this.setState({
      id: null,
      phone: "",
      scheduleDate: "",
      messageContent: "",

      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newNotice}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="phone">Phone</label>
              <input
                type="text"
                className="form-control"
                id="phone"
                required
                value={this.state.phone}
                onChange={this.onChangePhone}
                name="phone"
              />
            </div>

            <form className="form-group" noValidate>
              <TextField
                id="datetime-local"
                label="Next appointment"
                type="datetime-local"
                defaultValue="2021-05-24T10:30"
                className="form-group"
                InputLabelProps={{
                  shrink: true,
                }}
              />
            </form>

            <button onClick={this.saveNotice} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}
