import React from "react";
import HomePage from "../pages/HomePage/HomePage";
import NotFoundPage from "../pages/NotFoundPage/NotFoundPage";
import BookListPage from "../pages/BookListPage/BookListPage";
import BillListPage from "../pages/BillListPage/BillListPage";
import ActionPage from "../pages/ActionPage/ActionPage";
import ActionBillPage from "../pages/ActionPage/ActionBillPage";
import RegisterPage from "../pages/RegisterPage/RegisterPage";
import LoginPage from "../pages/LoginPage/LoginPage";
const routes = [
  {
    path: "/",
    exact: true,
    main: ({ history }) => <LoginPage history={history}></LoginPage>,
  },
  {
    path: "/login",
    exact: false,
    main: ({ history }) => <LoginPage history={history}></LoginPage>,
  },
  {
    path: "/home",
    exact: true,
    main: () => <HomePage></HomePage>,
  },
  {
    path: "/books",
    exact: true,
    main: () => <BookListPage></BookListPage>,
  },
  {
    path: "/book/add",
    exact: true,
    main: ({ history }) => <ActionPage history={history}></ActionPage>,
  },
  {
    path: "/book/:id/edit",
    exact: false,
    main: ({ match, history }) => (
      <ActionPage match={match} history={history}></ActionPage>
    ),
  },
  {
    path: "/bills",
    exact: true,
    main: () => <BillListPage></BillListPage>,
  },
  {
    path: "/bill/add",
    exact: true,
    main: ({ history }) => <ActionBillPage history={history}></ActionBillPage>,
  },
  {
    path: "/bill/:id/edit",
    exact: false,
    main: ({ match, history }) => (
      <ActionPage match={match} history={history}></ActionPage>
    ),
  },
  {
    path: "/register",
    exact: true,
    main: ({ history }) => <RegisterPage history={history}></RegisterPage>,
  },
  {
    path: "",
    exact: false,
    main: () => <NotFoundPage></NotFoundPage>,
  },
];

export default routes;
