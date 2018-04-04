import React, { Component } from 'react';
import {
    Button, Container, Grid, Header, Icon, Image, Item, Label, Menu, Segment, Step, Table,
  } from 'semantic-ui-react';
class DriverList extends Component {
    render() { 
        const style = {
            h1: {
              marginTop: '3em',
            },
            h2: {
              margin: '4em 0em 2em',

            },
            h3: {
              marginTop: '2em',
              padding: '2em 0em',
              color:'red',
            },
            last: {
              marginBottom: '300px',
            },
          }
        return ( 
            <div>
                      <Header
                as='h3'
                content='DriverList from API'
                style={style.h3}
                textAlign='center'
                />
              <Container text>
              <Segment.Group>
              <Segment>DriverName:{this.props.driver.name}</Segment>
              <Segment>type:{this.props.driver.type}</Segment>
              <Segment>LicenseNo:{this.props.driver.license_number}</Segment>
              <Segment>Exp:{this.props.driver.expiration_date}</Segment>
              <Segment>last_updated_date:{this.props.driver.last_updated_date}</Segment>
              <Segment>last_updated_time:{this.props.driver.last_updated_time}</Segment>
              

             </Segment.Group>
             <Button onClick={this.handleCreateSubmit} color='red'>AddList

              </Button>
              {/* <div>
            <button
                onClick={ this.handleCreateSubmit}>
                Add to Renewallist 
            </button>
        </div> */}
           </Container>
               
            </div>
         )
    }
}
 
export default DriverList;