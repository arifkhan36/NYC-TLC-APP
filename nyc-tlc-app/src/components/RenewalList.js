import React, { Component } from 'react';
import {
    Button, Container, Grid, Header, Icon, Image, Item, Label, Menu, Segment, Step, Table,
  } from 'semantic-ui-react';

class RenewalList extends Component {
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
            },
            last: {
              marginBottom: '300px',
            },
          }
        return ( 
            <div>
                <Header
                as='h3'
                content='Renewal DriverList'
                style={style.h3}
                textAlign='center'
                />
              <Container text>
              <Segment.Group>
              <Segment>Name: {this.props.name} </Segment>
              <Segment>LicenseNumber: {this.props.license_number}</Segment>
              <Segment>type: {this.props.type}</Segment>
              <Segment>expirationDate: {this.props.expiration_date}</Segment>
              <Segment>last_updated_date: {this.props.last_updated_date}</Segment>
              <Segment>last_updated_time: {this.props.last_updated_time}</Segment>
              

             </Segment.Group>
             <Button onClick={() => this.props.deleteRenewal(this.props.id, this.props.index)} color='red'>Delete

              </Button>
           </Container>

               

            </div>
         )
    }
}
 
export default RenewalList;