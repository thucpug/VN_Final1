import React, { Component } from "react";
import { Link } from "react-router-dom";
class BookItem extends Component {
  onRemove = id => {
    this.props.onRemove(id);
  };
  render() {
    var { book, index } = this.props;
    console.log(book);

    return (
      <tr>
        <td>{index + 1}</td>
        <td>{book.nameBook}</td>
        <td>{book.kindDTO.kindName}</td>
        <td>{book.authorDTO.authorName}</td>
        <td>{book.languagesDTO.language}</td>
        <td>{book.amounts}</td>
        <td>
          <img
            className="imgbook"
            src={`http://localhost:8080/api/images/${book.image}`}
          ></img>
        </td>
        <td>{book.createDate}</td>
        <td>
          <span className={`label label-${book.status ? "success" : "danger"}`}>
            {book.status ? "TRUE" : "FALSE"}
          </span>
        </td>
        <td>
          <Link to={`/book/${book.id}/edit`} className="btn btn-success mr-10">
            Edit
          </Link>
          <button
            type="button"
            className="btn btn-danger"
            onClick={() => this.onRemove(book.id)}
          >
            Remove
          </button>
        </td>
      </tr>
    );
  }
}

export default BookItem;
