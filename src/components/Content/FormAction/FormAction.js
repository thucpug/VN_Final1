import React, { Component } from "react";
import axios from "axios";
import {
  actAddBookRequest,
  actFetchBookRequest,
  actUpdateBookRequest,
  actAddBookImageRequest,
} from "../../../redux/actions";
import { connect } from "react-redux";
import ApiService from "../../../utils/ApiService";
class FormAction extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      nameBook: "",
      contentsBook: "",
      UrlImage: null,
      amounts: 0,
      papers: 1,
      status: false,
      authorDTO: 1,
      kindDTO: 1,
      languagesDTO: 1,
    };
  }
  componentDidMount() {
    var { match } = this.props;
    if (match) {
      var id = match.params.id;
      console.log(id);
      this.props.onEditBook(id);
    }
  }
  UNSAFE_componentWillReceiveProps(nextProps) {
    if (nextProps && nextProps.itemEditing) {
      var { itemEditing } = nextProps;
      console.log(itemEditing);

      this.setState({
        id: itemEditing.id,
        nameBook: itemEditing.nameBook,
        contentsBook: itemEditing.contentsBook,
        UrlImage: itemEditing.image,
        amounts: itemEditing.amounts,
        papers: itemEditing.papers,
        status: itemEditing.status,
        authorDTO: itemEditing.authorDTO.id,
        kindDTO: itemEditing.kindDTO.id,
        languagesDTO: itemEditing.languagesDTO.id,
      });
    }
  }
  onClick = () => {
    console.log(this.state);
  };
  onChange = e => {
    var target = e.target;
    var name = target.name;
    var value = target.type === "checkbox" ? target.checked : target.value;
    console.log(name + "-" + value);

    this.setState({
      [name]: value,
    });
  };
  onFileChangeHandler = e => {
    this.setState({
      UrlImage: e.target.files[0],
    });
  };
  upLoadFile = () => {
    const formData = new FormData();
    formData.append("file", this.state.UrlImage, this.state.UrlImage.name);
    console.log(this.state.UrlImage);
    ApiService.onuploadFile(formData).then(res => {
      console.log(res.data);
    });
  };
  onSubmit = e => {
    var { history } = this.props;
    var {
      id,
      nameBook,
      contentsBook,
      UrlImage,
      papers,
      amounts,
      kindDTO,
      authorDTO,
      languagesDTO,
      status,
    } = this.state;
    var book = {
      id: id,
      nameBook: nameBook,
      contentsBook: contentsBook,
      image: UrlImage.name,
      papers: parseInt(papers),
      status: status,
      amounts: parseInt(amounts),
      authorDTO: {
        id: parseInt(authorDTO),
      },
      kindDTO: {
        id: parseInt(kindDTO),
      },
      languagesDTO: {
        id: parseInt(languagesDTO),
      },
    };
    console.log(book);
    if (book.id) {
      this.props.onUpdateBook(book);
      history.push("/books");
    } else {
      this.props.onAddBook(book);
      history.goBack();
    }
  };
  render() {
    var {
      nameBook,
      contentsBook,
      papers,
      amounts,
      kindDTO,
      authorDTO,
      languagesDTO,
      status,
    } = this.state;

    return (
      <div className="box box-primary">
        <div className="box-header with-border">
          <h3 className="box-title">Action Change Book</h3>
        </div>
        <form>
          <div className="box-body">
            <div className="form-group">
              <label htmlFor="exampleInputEmail1">Book's Name</label>
              <input
                type="text"
                name="nameBook"
                value={nameBook}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputEmail1"
                placeholder="Enter Name"
              />
            </div>
            <div className="form-group">
              <label htmlFor="exampleInputPassword1">Content Book</label>
              <input
                type="text"
                name="contentsBook"
                value={contentsBook}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputPassword1"
                placeholder="Content"
              />
            </div>
            <div className="form-group">
              <label htmlFor="exampleInputPassword1">Pager</label>
              <input
                type="number"
                name="papers"
                value={papers}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputPassword1"
                placeholder="Number Page"
              />
            </div>
            <div className="form-group">
              <label htmlFor="exampleInputPassword1">Amounts</label>
              <input
                type="number"
                name="amounts"
                value={amounts}
                onChange={this.onChange}
                className="form-control"
                id="exampleInputPassword1"
                placeholder="Amount Book"
              />
            </div>
            <div className="form-group">
              <label>Kind</label>
              <select
                name="kindDTO"
                value={kindDTO}
                onChange={e => this.onChange(e)}
                className="form-control"
              >
                <option value={1}>Ngụ ngôn</option>
                <option value={2}>HaiHuoc</option>
                <option value={3}>Khoa hoc</option>3
                <option value={4}>Tin Hoc</option>
              </select>
            </div>
            <div className="form-group">
              <label>Author</label>
              <select
                name="authorDTO"
                value={authorDTO}
                onChange={e => this.onChange(e)}
                className="form-control"
              >
                <option value={1}>Thuc</option>
                <option value={2}>Thuc2</option>
                <option value={3}>Thuc3</option>
              </select>
            </div>
            <div className="form-group">
              <label>Language</label>
              <select
                name="languagesDTO"
                value={languagesDTO}
                onChange={e => this.onChange(e)}
                className="form-control"
              >
                <option value={1}>Vietnamese</option>
                <option value={2}>English</option>
              </select>
            </div>
            <div className="form-group">
              <div className="row">
                <div className="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                  <label>Book's image</label>
                  <input
                    type="file"
                    name="file"
                    multiple
                    onChange={this.onFileChangeHandler}
                    id="exampleInputFile"
                  />
                </div>
                <div className="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                  <button
                    type="button"
                    onClick={this.upLoadFile}
                    className="btn btn-danger"
                  >
                    Upload
                  </button>
                </div>
              </div>
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
}
const mapStateToProps = state => {
  return {
    itemEditing: state.itemEditing,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    onAddBook: book => {
      dispatch(actAddBookRequest(book));
    },
    onEditBook: id => {
      dispatch(actFetchBookRequest(id));
    },
    onUpdateBook: book => {
      dispatch(actUpdateBookRequest(book));
    },
    onUploadFile: image => {
      dispatch(actAddBookImageRequest(image));
    },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(FormAction);
