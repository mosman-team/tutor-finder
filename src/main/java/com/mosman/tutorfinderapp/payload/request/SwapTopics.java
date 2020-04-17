package com.mosman.tutorfinderapp.payload.request;

public class SwapTopics {

    private int firstTopicId;
    private int secondTopicId;

    public int getFirstTopicId() {
        return firstTopicId;
    }

    public void setFirstTopicId(int firstTopicId) {
        this.firstTopicId = firstTopicId;
    }

    public int getSecondTopicId() {
        return secondTopicId;
    }

    public void setSecondTopicId(int secondTopicId) {
        this.secondTopicId = secondTopicId;
    }
}
