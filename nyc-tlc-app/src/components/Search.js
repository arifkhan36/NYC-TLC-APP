import React, { Component } from 'react';

class Search extends Component {

   


    render() {
        return (
        <div className="Box">

             <form 
             onSubmit={this.props.submitForm}
             >

            <div className="ui icon input" >
                <input type="text" 
                
                value={this.props.inputValue}
                placeholder="Search..." 
                onChange={this.props.handleInputValue} 

               />
               <i aria-hidden="true" className="search icon" ></i>
            </div>
            </form>
          {/* <form 
            className="search"
            onSubmit={this.props.submitForm}
          >
           
          <input id="search"
            type="text"
            value={this.props.inputValue}
            
            name="content"
            placeholder="Search here"
            onChange={this.props.handleInputValue}
          />
          

          </form> */}
        </div>
        
        );
      }
    }

 
export default Search;