import React, { Component } from "react";
import { connect } from "react-redux";
import {
  actFetchBooksRequest,
  actAddBillRequest,
} from "../../../redux/actions";
import Calendar from "react-calendar";
class FormActionBill extends Component {
  constructor(props) {
    super(props);
    this.state = {
      namePerson: "",
      contentsBill: "",
      createDateEnded: new Date(),
      status: true,
      counts: 0,
      book_id: 0,
      book_billDTOS: [],
      Books: [],
      CountsOfBookSellect: 0,
    };
  }
  findIndex = (objs, id) => {
    var result = -1;
    objs.forEach((item, index) => {
      if (item.id == id) {
        return (result = index);
      }
    });
    return result;
  };
  componentDidMount() {
    console.log("Dfsdf");

    this.props.onFetchBooks(1, 20);
  }
  UNSAFE_componentWillReceiveProps(nextProps) {
    if (nextProps && nextProps.books) {
      var { books } = nextProps;
      console.log(books);

      this.setState({
        Books: books,
        CountsOfBookSellect: books[this.state.book_id].amounts,
      });
    }
  }
  onChange = e => {
    var target = e.target;
    var name = target.name;
    var value = target.type === "checkbox" ? target.checked : target.value;
    console.log(name + " " + value);
    if (name == "counts") {
      if (value > this.state.CountsOfBookSellect) {
        alert("You can't get more book than curently!");
        value = this.state.CountsOfBookSellect;
      }
    }
    this.setState({
      [name]: value,
    });
  };
  onChangeSL = e => {
    var target = e.target;
    var name = target.name;
    var value = target.type === "checkbox" ? target.checked : target.value;
    console.log(name + " " + value);
    var index = this.findIndex(this.state.Books, value);
    console.log(index);
    this.setState({
      [name]: value,
      CountsOfBookSellect: this.state.Books[index].amounts,
    });
  };
  onChangeDate = date => {
    this.setState({
      createDateEnded: date,
    });
    console.log(this.state.createDateEnded);
  };
  onConfirm = () => {
    var { book_id, counts } = this.state;
    this.state.book_billDTOS.push({ book_id: book_id, counts: counts });
    console.log(this.state.book_billDTOS);
  };
  onSubmit = e => {
    e.preventDefault();
    var { history } = this.props;
    var {
      namePerson,
      contentsBill,
      createDateEnded,
      status,
      book_billDTOS,
    } = this.state;
    var bill = {
      namePerson: namePerson,
      contentsBill: contentsBill,
      createDateEnded: createDateEnded,
      status: status,
      book_billDTOS: book_billDTOS,
    };
    console.log(bill);
    this.props.onAddBill(bill);
    history.goBack();
  };
  render() {
    var {
      namePerson,
      contentsBill,
      createDateEnded,
      status,
      Books,
      counts,
      book_id,
      CountsOfBookSellect,
    } = this.state;

    return (
      <div className="box box-primary">
        <div className="box-header with-border">
          <h3 className="box-title">Action Change Bill</h3>
        </div>
        <form>
          <div className="box-body">
            <div className="form-group">
              <label htmlFor="exampleInputEmail1">Bill's Name</label>
              <input
                type="text"
                name="namePerson"
                value={namePerson}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputEmail1"
                placeholder="namePerson"
              />
            </div>
            <div className="form-group">
              <label htmlFor="exampleInputPassword1">Content Bill</label>
              <input
                type="text"
                name="contentsBill"
                value={contentsBill}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputPassword1"
                placeholder="Content"
              />
            </div>
            <div className="form-group">
              <div className="row">
                <div className="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                  <label>Books:</label>
                  <select
                    name="book_id"
                    value={book_id}
                    onChange={e => this.onChangeSL(e)}
                    className="form-control"
                  >
                    {this.showBooks(Books)}
                  </select>
                  <span className="label label-warning ">
                    Amount'Book : {CountsOfBookSellect}
                  </span>
                </div>
                <div className="col-xs-2 col-sm-2 col-md-2 col-lg-2 ">
                  <label htmlFor="exampleInputPassword1">Amounts</label>
                  <input
                    type="number"
                    name="counts"
                    value={counts}
                    onChange={this.onChange}
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="Amount Book"
                  />
                </div>
                <div className="col-xs-2 col-sm-2 col-md-2 col-lg-2 ">
                  <label htmlFor="exampleInputPassword1">Accept:</label>
                  <br></br>
                  <button
                    type="button"
                    onClick={this.onConfirm}
                    className="btn btn-danger"
                  >
                    Confirm
                  </button>
                </div>
              </div>
            </div>
            <div className="form-group">
              <label htmlFor="exampleInputPassword1">Date Pay:</label>
              <Calendar
                name="createDateEnded"
                onChange={this.onChangeDate}
                value={createDateEnded}
              />
            </div>
            <div className="checkbox">
              <label>
                <input
                  type="checkbox"
                  name="status"
                  value={status}
                  checked={status}
                  onChange={e => this.onChange(e)}
                />
                Status
              </label>
            </div>
          </div>
          {/* /.box-body */}
          <div className="box-footer">
            <button
              type="button"
              onClick={this.onSubmit}
              className="btn btn-primary"
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    );
  }
  showBooks = listBook => {
    var result = null;
    if (listBook) {
      if (listBook.length > 1) {
        result = listBook.map((bookitem, index) => {
          return (
            <option key={index} value={bookitem.id}>
              {bookitem.nameBook}
            </option>
          );
        });
      }
    }
    return result;
  };
}
const mapStateToProps = state => {
  return {
    books: state.books,
    //itemEditing: state.itemEditing,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    onFetchBooks: (page, limit) => {
      dispatch(actFetchBooksRequest(page, limit));
    },
    onAddBill: bill => {
      dispatch(actAddBillRequest(bill));
    },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(FormActionBill);
