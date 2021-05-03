import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import NoticesList from "./components/notices.component";
import CreateNotice from "./components/create-notice.component";
import Notice from "./components/notice.component";

export default function Routes() {
  return (
    <Router>
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/notices"} className="navbar-brand">

          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/notices"} className="nav-link">
                Notices
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/notices"]} component={NoticesList} />
            <Route exact path="/add" component={CreateNotice} />
            <Route path="/notices/:id" component={Notice} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}