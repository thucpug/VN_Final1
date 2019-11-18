import React, { Component } from "react";
import BookList from "../../components/Content/BookList/BookList";
import { connect } from "react-redux";
import { actFetchBooksRequest, actDeleteBookRequest } from "../../redux/actions";
import BookItem from "../../components/Content/BookList/BookItem";
class BookListPage extends Component {
  componentDidMount() {
    this.props.fetchAllBooks(1, 20);
  }
  onRemove = id => {
    this.props.onDeleteBook(id);
  };
  render() {
    var { books } = this.props;
    return (
      <div>
        <BookList>{this.showBooks(books)}</BookList>
      </div>
    );
  }
  showBooks = books => {
    var result = null;
    if (books.length > 1) {
      result = books.map((book, index) => {
        return (
          <BookItem
            key={index}
            book={book}
            index={index}
            onRemove={this.onRemove}
          ></BookItem>
        );
      });
    }

    return result;
  };
}
const mapStateToProps = state => {
  return {
    books: state.books,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    fetchAllBooks: (page, limit) => {
      dispatch(actFetchBooksRequest(page, limit));
    },
    onDeleteBook:(id)=>{
      dispatch(actDeleteBookRequest(id));
    }
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(BookListPage);
