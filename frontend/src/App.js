import React from 'react';
import './App.scss';
import ComplexNumberRecordingForm from "./component/ComplexNumberRecordingForm";
import ComplexNumberList from "./component/ComplexNumberList";
import CustomerList from "./component/CustomerList";
import CustomerRecordingForm from "./component/CustomerRecordingForm";


function App() {
  return (
    <div className={["App","container"]}>
        <div className={"row"}>
            <div className={"col-md-3"}></div>
            <div className={"col-md-6"}>
                <CustomerRecordingForm/>
                <CustomerList/>
            </div>
            <div className={"col-md-3"}></div>
        </div>
    </div>
  );
}

export default App;
