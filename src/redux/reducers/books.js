import * as Types from "./../constants/index";
var initialState = [];

var findIndex = (objs, id) => {
  var result = -1;
  objs.forEach((item, index) => {
    if (item.id === id) {
      return (result = index);
    }
  });
  return result;
};
const books = (state = initialState, action) => {
  var index = -1;
  switch (action.type) {
    case Types.FETCH_BOOKS:
      state = action.books.listResult;
      return [...state];

    case Types.DELETE_BOOK:
      index = findIndex(state, action.book.data.id);
      if (index !== -1) {
        state.splice(index, 1);
      }
      return [...state];

    case Types.ADD_BOOK:
      var book = action.book.data;
      state.push(book);
      return [...state];

    case Types.UPDATE_BOOK:
      console.log(action.book.data);

      index = findIndex(state, action.book.data.id);
      state[index] = action.book.data;
      console.log(state);
      
      return [...state];

    default:
      return [...state];
  }
};
export default books;
