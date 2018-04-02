import React, { Component } from 'react';

class DriverList extends Component {
    render() { 
        return ( 
            <div>
               <p>DriverName:{this.props.driver.name}</p>
               <p>type:{this.props.driver.type}</p>
               <p>LicenseNo:{this.props.driver.license_number}</p>
               <p>Exp:{this.props.driver.expiration_date}</p>
               <p>last_updated_date:{this.props.driver.last_updated_date}</p>
               <p>last_updated_time:{this.props.driver.last_updated_time}</p>
               
            </div>
         )
    }
}
 
export default DriverList;