import React from "react";
import { Nav, Navbar} from "react-bootstrap";

class Sidebar extends React.Component{
    render() {
        return(

            <Nav className="col-md-12 d-none d-md-block bg-light sidebar">
                <div className="sidebar-sticky"></div>
                <Nav.Item>
                    <Nav.Link href="/slot/status">Kezdőlap</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link disabled>dummy</Nav.Link>
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