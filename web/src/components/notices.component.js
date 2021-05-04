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
    };
  }

  componentDidMount() {
    this.retrieveNotices();
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

  removeAllNotices() {
    NoticeService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }


  render() {
    const { searchTitle, notices, currentNotice, currentIndex } = this.state;

    return (
      <div className="list row">
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
                  {notice.title}
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
                  <strong>Title:</strong>
                </label>{" "}
                {currentNotice.title}
              </div>
              <div>
                <label>
                  <strong>Description:</strong>
                </label>{" "}
                {currentNotice.description}
              </div>
              <div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}
                {currentNotice.published ? "Published" : "Pending"}
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
