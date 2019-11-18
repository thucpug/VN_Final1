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
const bills = (state = initialState, action) => {
  var index = -1;
  switch (action.type) {
    case Types.FETCH_BILLS:
      state = action.bills.listResult;
      console.log(state);
      return [...state];
    case Types.DELETE_BILL:
      index = findIndex(state, action.bill.id);
      state.splice(index, 1);
      return [...state];
    case Types.ADD_BILL:
      state.push(action.bill);
      return [...state];
    default:
      return [...state];
  }
};
export default bills;
