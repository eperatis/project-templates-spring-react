import React from 'react';
import './App.scss';
import {Container, Row, Col } from "react-bootstrap";
import {
    BrowserRouter as Router,
    Route,
    Switch,
    Redirect
} from 'react-router-dom';

//Pages
import Sidebar from "./component/Sidebar";
import ComplexNumberRecordingForm from "./component/ComplexNumberRecordingForm";
import ComplexNumberList from "./component/ComplexNumberList";
import CustomerList from "./component/CustomerList";
import CustomerRecordingForm from "./component/CustomerRecordingForm";
import NotFound from "./component/NotFound";



function App() {
  return (
      <Router>
          <Container fluid>
              <Row>
                  <Col xs={2} id="sidebar-wrapper">
                      <Sidebar />
                  </Col>
                  <Col  xs={10} id="page-content-wrapper">
                      <div className={["App","container"]}>
                          <div className={"row"}>
                              <div className={"col-md-3"}></div>
                              <div className={"col-md-6"}>
                                  <Switch>
                                      {/*<Route exact path="/slot/status" component={Slots}/>*/}
                                      <Route exact path="/customer" component={CustomerList}/>
                                      <Route exact path="/customer/record" component={CustomerRecordingForm}/>
                                      <Route exact path="/404" component={NotFound}/>
                                      <Redirect to="/404"/>
                                      <CustomerRecordingForm/>
                                      <CustomerList/>
                                  </Switch>
                              </div>
                              <div className={"col-md-3"}></div>
                          </div>
                      </div>
                  </Col>
              </Row>

          </Container>
      </Router>
  );
}

export default App;
