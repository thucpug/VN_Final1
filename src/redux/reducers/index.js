import { combineReducers } from "redux";
import books from "./books";
import itemEditing from "./itemEditing";
import bills from "./bills";
import auth from "./auth";
const myReducers = combineReducers({
  books,
  itemEditing,
  bills,
  auth,
});
export default myReducers;
