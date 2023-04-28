import React, { useEffect, useState } from "react";
import { getMessages } from "../../services/api/Messages";
import Message from "../Message";
const Messages = (props) => {
  const [messagesList, setMessagesList] = useState([]);
  useEffect(() => {
    getMessages()
      .then((data) => setMessagesList(data));
  }, [props]);

  const cardsCompo = messagesList? messagesList.map((message) => <Message message={message} key={message.id}/>) :"Loading...";

  return (
    <div>
      <div className="album py-8 bg-light">
      <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div className="jumbotron">
            <h1 className="display-4">Hello!</h1>
            <p className="lead">
              You might have new messages
            </p>
            
          </div>
        </div><div className="container">
          <div className="row row-cols-1">
            {cardsCompo}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Messages;
