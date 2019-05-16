import React from 'react';

// single page React component 
class Home extends React.Component {

   constructor(props) {
      super(props);
  
      // declare variables applicable
      this.state = {
         showComponent: false,
         curs: [{value: 'currency'}],
         currency1: [],
         fromdate: '',
         todate: '',
         currencyname: 'All',
         curTypes: []
       };
       // bind functions
       this.handleChange = this.handleChange.bind(this);
       this.handleChangeto = this.handleChangeto.bind(this);
       this.handleChangename = this.handleChangename.bind(this);
       this._onButtonClick = this._onButtonClick.bind(this);
       
    }
    
    // call method on page load
    componentDidMount() {
      this.currencyList();
    }

    _onButtonClick(event) {
      this.setState({
        showComponent: true,
      });
     
    
      $.getJSON('http://localhost:8102/get/'+this.state.fromdate+'/'+this.state.todate+'/'+this.state.currencyname)
        .then(({ results }) => this.setState({ currency1: results }));
    }
    handleChange(event) {
      this.setState({fromdate: event.target.value});
    }
    handleChangeto(event) {
      this.setState({todate: event.target.value});
    }
    handleChangename(event) {
      //alert('A name +
      this.setState({currencyname: event.target.value});
    }
  
    currencyList() {
      $.getJSON('http://localhost:8102/getAll')
        .then(({ result }) => this.setState({ curTypes: result }));
    }
    render() {
      const curslist = this.state.curs.map((item, i) => (
        <div>
          <form onSubmit={this._onButtonClick}>
          <label>From Date </label><input type="date"  value={this.state.fromdate} onChange={this.handleChange}></input>
          <label>To Date </label><input type="date" value={this.state.todate} onChange={this.handleChangeto}></input>
          <label>Currency </label>
          <select value={this.state.currencyname} onChange={this.handleChangename}>
          <option>All</option>
          <option>BTC</option>
          <option>LTC</option>
          <option>ETC</option>
          
          </select>
          <input type="submit" class="btn btn-default" value="Submit" />
          </form>
        </div>
        
      ));

      const currencie = this.state.curs.map((item, i) => (
         <div>  
           {this.state.showComponent ?           
           

             <div className="col">
             <table class="table"> 
            <thead><th width="100px">Currency</th>
                <th width="100px">Max Profit</th>
               <th width="100px">From Time</th>
               <th width="100px">To Time</th>
               <th width="100px">From Price</th>
               <th width="100px">To Price</th>
               <th width="100px">Date</th>
               </thead>
               </table>
            
             {this.state.currency1.map(cur => 
             <div><table class="table">            
                
               <tbody>
               <tr><td width="100px">{cur.currency}</td>
               <td width="100px">{cur.valueProfit}</td>
               <td width="100px">{cur.fromTime}</td>
               <td width="100px">{cur.toTime}</td>
               <td width="100px">{cur.frmPrice}</td>
               <td width="100px">{cur.toPrice}</td>
               <td width="100px">{cur.date}</td>
               </tr></tbody></table></div>)}
           </div>:
            null
         }
         </div>
         
       ));
  
      return (
        <div id="layout-content" className="layout-content-wrapper">
          <div className="panel-list">{ curslist }</div>
          <div className="panel-list">{ currencie }</div>
         
        </div>
      );
      
}
}

export default Home;