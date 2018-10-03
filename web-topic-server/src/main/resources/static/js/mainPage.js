

document.onload = fetchTopics();


function fetchTopics(){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "api/topic");

    xhr.onload = function(){
        if (this.status == 200){
            console.log(this.responseText);
             var response = JSON.parse(this.responseText);
            

             output = '';

             output+='<ul>';

            for (let i = 0; i < 5; i++) {
                output+='<li>';   
                output+='description: ' + response[i].description;
                output+=' created: ' +  response[i].timestamp;

                output+='</li>';             
            }
             output+='</ul>';

             var allTopicsElement = document.getElementsByClassName('allTopics')[0];
             allTopicsElement.innerHTML = output;

        }
    }
    xhr.send();

    
   
}