import React from 'react';
import './App.scss';
import {
    BrowserRouter as Router,
    Route,
    Switch,
    Redirect
} from 'react-router-dom';

//Pages
import Sidebar from "./component/Sidebar";
import CustomerList from "./component/CustomerList";
import CustomerRecordingForm from "./component/CustomerRecordingForm";
import NotFound from "./component/NotFound";

import Map from "./component/Map";


function App() {
    return (
        <Router>
            <div className={"row"}>
                <div className={"col-md-2"}>
                    <Sidebar/>
                </div>
                <div className={"col-md-8"}>
                    <Switch>
                        {<Route exact path="/" component={Map}/>}
                        <Route exact path="/customer" component={CustomerList}/>
                        <Route exact path="/customer/record" component={CustomerRecordingForm}/>
                        <Route exact path="/404" component={NotFound}/>
                        <Redirect to="/404"/>
                        <CustomerRecordingForm/>
                        <CustomerList/>
                    </Switch>
                </div>
                <div className={"col-md-2"}/>
            </div>
        </Router>
    );
}

export default App;
