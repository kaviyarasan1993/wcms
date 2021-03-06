import React, {Component} from 'react';
import {connect} from 'react-redux';
import { withRouter } from 'react-router'

import RaisedButton from 'material-ui/RaisedButton';




class UiBackButton extends Component {
   constructor(props) {
        super(props);
       }

	 back=(e)=>
	 {
				 let {setRoute}=this.props;
         setRoute("/"+localStorage.getItem("returnUrl"));
	 }

   render () {
		 let {back}=this;
      return (
				<div style={{"textAlign": "right",
				"paddingRight": "15px"}}>
					<br/>
						<RaisedButton type="button" onClick={(e)=>{
							back(e)
						}} primary={true} label={"Back"} />
					<br/>
					<br/>
				</div>
       );
   }
}

const mapStateToProps = state => ({
});

const mapDispatchToProps = dispatch => ({
  setRoute: (route) => dispatch({type: "SET_ROUTE", route})
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(UiBackButton));
