import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";

//store
import { createStore, compose, applyMiddleware } from "redux";
import { Provider } from "react-redux";
import myReducer from "./redux/reducers/index";
import thunk from "redux-thunk";
import setAuth from "./utils/setAuthToken";

setAuth.setHeaderPostJson();
setAuth.setAuthrizationToken(localStorage.getItem("accessToken"));

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(myReducer, composeEnhancer(applyMiddleware(thunk)));
ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root"),
);
serviceWorker.unregister();
