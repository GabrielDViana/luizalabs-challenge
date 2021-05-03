import React, { Component } from "react";
import NoticeService from "../services/notice.service";
import { Link } from "react-router-dom";

export default class NoticesList extends Component {
  constructor(props) {
    super(props);
    this.retrieveNotices = this.retrieveNotices.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveNotice = this.setActiveNotice.bind(this);

    this.state = {
      notices: [],
      currentNotice: null,
      currentIndex: -1,
      searchTitle: ""
    };
  }

  componentDidMount() {
    this.retrieveNotices();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }

  retrieveNotices() {
    NoticeService.getAll()
      .then(response => {
        this.setState({
          notices: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveNotices();
    this.setState({
      currentNotice: null,
      currentIndex: -1
    });
  }

  setActiveNotice(notice, index) {
    this.setState({
      currentNotice: notice,
      currentIndex: index
    });
  }

  render() {
    const { searchTitle, notices, currentNotice, currentIndex } = this.state;

    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by phone"
              value={searchTitle}
              onChange={this.onChangeSearchTitle}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchTitle}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <h4>Notices List</h4>

          <ul className="list-group">
            {notices &&
              notices.map((notice, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveNotice(notice, index)}
                  key={index}
                >
                  {notice.phone}
                </li>
              ))}
          </ul>

        </div>
        <div className="col-md-6">
          {currentNotice ? (
            <div>
              <h4>Notice</h4>
              <div>
                <label>
                  <strong>Phone:</strong>
                </label>{" "}
                {currentNotice.phone}
              </div>
              <div>
                <label>
                  <strong>Date:</strong>
                </label>{" "}
                {currentNotice.scheduleDate}
              </div>
              <div>
                <label>
                  <strong>Message:</strong>
                </label>{" "}
                {currentNotice.messageContent}
              </div>

              <Link
                to={"/notices/" + currentNotice.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Notice...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
