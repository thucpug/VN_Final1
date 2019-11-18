import React, { Component } from "react";

class UserList extends Component {
  componentDidMount() {
    const script = document.createElement("script");
    script.src = `/static/js/content.js`;
    script.async = true;
    document.body.appendChild(script);
  }
  render() {
    return (
      <div>
        {/* <div className="content-wrapper"> */}
        {/* Content Header (Page header) */}
        <section className="content-header">
          <h1>
            List Bill Book!
            <small>bill mmanager</small>
          </h1>
          <ol className="breadcrumb">
            <li>
              <a href="fake_url">
                <i className="fa fa-dashboard" /> Home
              </a>
            </li>
            <li>
              <a href="fake_url">Tables</a>
            </li>
            <li className="active">ListBill</li>
          </ol>
        </section>
        {/* Main content */}
        <section className="content">
          <div className="row">
            <div className="col-xs-12">
              <div className="box">
                <div className="box-header">
                  <h3 className="box-title">ListBill</h3>
                </div>
                {/* /.box-header */}
                <div className="box-body">
                  <table
                    id="example1"
                    className="table table-bordered table-striped"
                  >
                    <thead>
                      <tr>
                        <th>*ID</th>
                        <th>PersonName</th>
                        <th>Password</th>
                        <th>CreateDate</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>1</td>
                        <td>Thuc</td>
                        <td>sdfsdfsafas</td>
                        <td>22/12/2019</td>
                        <td>
                          <span class="label label-warning">Active</span>
                        </td>
                      </tr>
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>#ID</th>
                        <th>PersonName</th>
                        <th>Password</th>
                        <th>CreateDate</th>
                        <th>Status</th>
                      </tr>
                    </tfoot>
                  </table>
                </div>
                {/* /.box-body */}
              </div>
              {/* /.box */}
            </div>
            {/* /.col */}
          </div>
          {/* /.row */}
        </section>
        {/* /.content */}
      </div>
      // </div>
    );
  }
}

export default UserList;
