import React, { Component } from "react";
import NoticeService from "../services/notice.service";

export default class Notice extends Component {
  constructor(props) {
    super(props);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onChangeDate = this.onChangeDate.bind(this);
    this.getNotice = this.getNotice.bind(this);
    this.updatePublished = this.updatePublished.bind(this);
    this.updateNotice = this.updateNotice.bind(this);
    this.deleteNotice = this.deleteNotice.bind(this);

    this.state = {
      currentNotice: {
        id: null,
        phone: "",
        scheduleDate: "",
        message: ""
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getNotice(this.props.match.params.id);
  }

  onChangePhone(e) {
    const phone = e.target.value;

    this.setState(function(prevState) {
      return {
        currentNotice: {
          ...prevState.currentNotice,
          phone: phone
        }
      };
    });
  }

  onChangeDate(e) {
    const scheduleDate = e.target.value;
    
    this.setState(prevState => ({
      currentNotice: {
        ...prevState.currentNotice,
        scheduleDate: scheduleDate
      }
    }));
  }

  getNotice(id) {
    NoticeService.get(id)
      .then(response => {
        this.setState({
          currentNotice: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  deleteNotice() {    
    NoticeService.delete(this.state.currentNotice.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/notices')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentNotice } = this.state;

    return (
      <div>
        {currentNotice ? (
          <div className="edit-form">
            <h4>Notice</h4>
            <form>
              <div className="form-group">
                <label htmlFor="phone">Phone</label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  value={currentNotice.phone}
                  onChange={this.onChangePhone}
                />
              </div>
              <div className="form-group">
                <label htmlFor="scheduleDate">Date</label>
                <input
                  type="text"
                  className="form-control"
                  id="scheduleDate"
                  value={currentNotice.scheduleDate}
                  onChange={this.onChangeDate}
                />
              </div>

            </form>

            <button
              className="badge badge-danger mr-2"
              onClick={this.deleteNotice}
            >
              Delete
            </button>

            <button
              type="submit"
              className="badge badge-success"
              onClick={this.updateNotice}
            >
              Update
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Notice...</p>
          </div>
        )}
      </div>
    );
  }
}
