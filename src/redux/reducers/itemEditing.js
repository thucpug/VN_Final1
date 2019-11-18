import * as Types from "./../constants/index";
var initialState = {};

const itemEditing = (state = initialState, action) => {
  switch (action.type) {
    case Types.EDIT_BOOK:
      state = action.book.data;
      console.log(state);
      
      return { ...state };
    default:
      return { ...state };
  }
};
export default itemEditing;
