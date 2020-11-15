import React from "react";
import {Nav} from "react-bootstrap";

class Sidebar extends React.Component {
    render() {
        return (
            <Nav className="col-md-12 d-none d-md-block bg-light sidebar">
                <Nav.Item>
                    <Nav.Link href="/">Kezdőlap</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link href="/free-slots">Szabad táborhelyek</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link href="/customer">Customer</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link href="/customer/record">Customer felvitele</Nav.Link>
                </Nav.Item>
            </Nav>
        )
    }
}

export default Sidebar;