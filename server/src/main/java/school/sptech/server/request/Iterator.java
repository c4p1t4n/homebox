package school.sptech.server.request;



import school.sptech.server.model.User;




public interface Iterator {


        public User getNext();


        public boolean hasNext();

        public void ResetList();
}
