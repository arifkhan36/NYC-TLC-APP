import React, {Component} from 'react';
import './Navbar.css';

class Navbar extends Component {

render() {

   return (
    <header>
      <h1><a>NYC TLC APP</a></h1>
      <nav>
         <li><a>All DriverList</a></li>
         <li><a href="/">Home</a></li>
         <li><a>About</a></li>
         <li><a href="/login">Login</a></li>

      </nav>





    </header>

    );

  }

}

export default Navbar;
