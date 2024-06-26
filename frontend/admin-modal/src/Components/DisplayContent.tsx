import React from "react";

interface DisplayContentProps {
  title: string;
}

const DisplayContent: React.FC<DisplayContentProps> = ({ title }) => {
  if (!title) {
    return "Error....";
  }
  return (
    <div>
      <h2>{title}</h2>
    </div>
  );
};

export default DisplayContent;
