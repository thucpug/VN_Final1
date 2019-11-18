import React, { Component } from "react";
import BillList from "../../components/Content/BillList/BillList";
import { connect } from "react-redux";
import BillItem from "../../components/Content/BillList/BillItem";
import {
  actFetchBillsRequest,
  actRemoveBillRequest,
} from "../../redux/actions";
class BillListPage extends Component {
  componentDidMount() {
    this.props.fetchAllBills(1, 20);
  }

  onRemove = id => {
    console.log(id);
    this.props.onRemoveBill(id);
  };
  onExportFile = id => {
    console.log(id);
    this.props.onExportFile(id);
  };
  render() {
    var { bills } = this.props;
    return (
      <div>
        <BillList>{this.showBillItem(bills)}</BillList>
      </div>
    );
  }
  showBillItem = bills => {
    var result = null;
    console.log(bills);
    if (bills.length > 1) {
      result = bills.map((bill, index) => {
        return (
          <BillItem
            key={index}
            bill={bill}
            index={index}
            onRemove={this.onRemove}
            onExportFile={this.onExportFile}
          ></BillItem>
        );
      });
    }
    return result;
  };
}
const mapStateToProps = state => {
  return {
    bills: state.bills,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    fetchAllBills: (page, limit) => {
      dispatch(actFetchBillsRequest(page, limit));
    },
    onRemoveBill: id => {
      dispatch(actRemoveBillRequest(id));
    },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(BillListPage);
