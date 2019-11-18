import * as Types from "./../constants/index";
import ApiService from "../../utils/ApiService";


//fetch books
export const actFetchBooksRequest = (page, limit) => {
  return dispatch => {
    return ApiService.callApi(
      `book?page=${page}&limit=${limit}`,
      "GET",
      null,
    ).then(res => {
      dispatch(actFetchBooks(res.data));
    });
  };
};
export const actFetchBooks = books => {
  return {
    type: Types.FETCH_BOOKS,
    books,
  };
};
//delete book
export const actDeleteBookRequest = id => {
  return dispatch => {
    return ApiService.callApi(`book/${id}`, "DELETE", null).then(res => {
      dispatch(actDeleteBook(res.data));
    });
  };
};
export const actDeleteBook = book => {
  return {
    type: Types.DELETE_BOOK,
    book,
  };
};
//add book
export const actAddBookRequest = book => {
  return dispatch => {
    return ApiService.callApi("book", "POST", book).then(res => {
      dispatch(actAddBook(res.data));
    });
  };
};
export const actAddBookImageRequest = image => {
  return dispatch => {
    return ApiService.callApi("image/upload", "POST", image).then(res => {
      console.log(res.data);
    });
  };
};
export const actAddBook = book => {
  return {
    type: Types.ADD_BOOK,
    book,
  };
};
//update product
export const actFetchBookRequest = id => {
  return dispatch => {
    return ApiService.callApi(`book/${id}`, "GET", null).then(res => {
      dispatch(actEditBook(res.data));
    });
  };
};
export const actEditBook = book => {
  return {
    type: Types.EDIT_BOOK,
    book,
  };
};
export const actUpdateBookRequest = book => {
  return dispatch => {
    return ApiService.callApi(`book/${book.id}`, "PUT", book).then(res => {
      dispatch(actUpdateBook(res.data));
    });
  };
};
export const actUpdateBook = book => {
  return {
    type: Types.UPDATE_BOOK,
    book,
  };
};

//fetch bill
export const actFetchBillsRequest = (page, limit) => {
  return dispatch => {
    return ApiService.callApi(
      `bill?page=${page}&limit=${limit}`,
      "GET",
      null,
    ).then(res => {
      dispatch(actFetchBills(res.data));
    });
  };
};
export const actFetchBills = bills => {
  return {
    type: Types.FETCH_BILLS,
    bills,
  };
};
//remove bill
export const actRemoveBillRequest = id => {
  return dispatch => {
    return ApiService.callApi(`bill/${id}`, "DELETE", null).then(res => {
      dispatch(actRemoveBill(res.data.data));
    });
  };
};
export const actRemoveBill = bill => {
  return {
    type: Types.DELETE_BILL,
    bill,
  };
};

//add bill
export const actAddBillRequest = bill => {
  return dispatch => {
    return ApiService.callApi("bill", "POST", bill).then(res => {
      console.log(res.data.data);
      dispatch(actAddBill(res.data.data));
    });
  };
};
export const actAddBill = bill => {
  console.log(bill);
  return {
    type: Types.ADD_BILL,
    bill,
  };
};

//LOGIN
export const actLoginRequest = user => {
  return dispatch => {
    return ApiService.callApi("auth/login", "POST", user).then(res => {
      dispatch(actLogin(res.data));
    });
  };
};
export const actLogin = token => {
  return {
    type: Types.LOGIN,
    token,
  };
};
