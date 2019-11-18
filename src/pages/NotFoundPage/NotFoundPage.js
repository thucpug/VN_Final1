import React, { Component } from "react";
import { Link } from "react-router-dom";
class NotFoundPage extends Component {
  render() {
    return (
      <div className="content-wrapper">
        {/* Content Header (Page header) */}

        {/* Main content */}
        <section className="content">
          <div className="error-page">
            <h2 className="headline text-yellow"> 404</h2>
            <div className="error-content">
              <h3>
                <i className="fa fa-warning text-yellow" /> Oops! Page not
                found.
              </h3>
              <p>
                We could not find the page you were looking for. Meanwhile, you
                may <Link to="/">return to dashboard</Link> or try using the
                search form.
              </p>
              <form className="search-form">
                <div className="input-group">
                  <input
                    type="text"
                    name="search"
                    className="form-control"
                    placeholder="Search"
                  />
                  <div className="input-group-btn">
                    <button
                      type="submit"
                      name="submit"
                      className="btn btn-warning btn-flat"
                    >
                      <i className="fa fa-search" />
                    </button>
                  </div>
                </div>
                {/* /.input-group */}
              </form>
            </div>
            {/* /.error-content */}
          </div>
          {/* /.error-page */}
        </section>
        {/* /.content */}
      </div>
    );
  }
}

export default NotFoundPage;
