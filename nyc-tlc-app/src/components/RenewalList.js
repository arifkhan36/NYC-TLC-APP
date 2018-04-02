import React, { Component } from 'react';

class RenewalList extends Component {

    render() { 
        return ( 
            <div>
                <ul>    
                  <li>Name: {this.props.name} </li>
                  <li>LicenseNumber: {this.props.license_number}</li>
                  <li>type: {this.props.type}</li>
                  <li>expirationDate: {this.props.expiration_date}</li>
                  <li>last_updated_date: {this.props.last_updated_date}</li>
                  <li>last_updated_time: {this.props.last_updated_time}</li>
                  
                </ul> 

                <button onClick={() => this.props.deleteRenewal(this.props.id, this.props.index)}>Delete

                </button>

            </div>
         )
    }
}
 
export default RenewalList;