import React, { Component } from "react";
import { Link } from "react-router-dom";
import BillHighItem from "./BillHighItem";
class BillItem extends Component {
  onRemove = id => {
    this.props.onRemove(id);
  };
  onExportFile = id => {
    this.props.onExportFile(id);
  };
  render() {
    var { bill, index } = this.props;
    return (
      <tr>
        <td>{index + 1}</td>
        <td>{bill.namePerson}</td>
        <td>{bill.createDate}</td>
        <td>{bill.createDateEnded}</td>
        <td>{this.showListBookRent(bill.book_billDTOS, true)}</td>
        <td>{this.showListBookRent(bill.book_billDTOS, false)}</td>
        <td>
          <span className={`label label-${bill.status ? "success" : "danger"}`}>
            {bill.status ? "TRUE" : "FALSE"}
          </span>
        </td>
        <td>
          <button className="btn btn-success mr-10">Edit</button>
          <button
            type="button"
            onClick={() => this.onRemove(bill.id)}
            className="btn btn-danger mr-10"
          >
            Remove
          </button>
          <a className="btn btn-primary" href={`http://localhost:8080/api/reportv2/${bill.id}`} target="_blank">
            Export
          </a>
        </td>
      </tr>
    );
  }
  showListBookRent = (listBook, bool) => {
    var result = null;
    if (listBook.length > 0) {
      result = listBook.map((bookitem, index) => {
        return (
          <BillHighItem
            key={index}
            bool={bool}
            bookitem={bookitem}
          ></BillHighItem>
        );
      });
    }
    return result;
  };
}

export default BillItem;
