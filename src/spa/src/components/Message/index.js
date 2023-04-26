const Message = (props) => {
  return (
    <div class="card">
      <div className="card-body">
        <h5 className="card-title">
          {props.message.sender.lastName}, {props.message.sender.firstName}
        </h5>
        <h6 className="card-subtitle mb-2 text-muted">
          {props.message.date} - {props.message.time}
        </h6>
        <p className="card-text">{props.message.message}</p>
        <button className="card-link">Reply</button>
      </div>
    </div>
  );
};
export default Message;
